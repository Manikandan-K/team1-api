#!/bin/bash

tagName=$1
securityKeyName=$2
commitHashOfDeployable=$(git rev-parse --short HEAD)

function createElasticIPAndSecurityGroup() {
    cfn-create-or-update \
      --stack-name api-common-${tagName} \
      --template-body file://buildAndDeploy/deploy/api-ec2-common.yml \
      --parameters ParameterKey=tagName,ParameterValue=${tagName} \
      --wait || exit 1
}

function deleteOldAPIDeploymentStackIfExists() {
    aws cloudformation describe-stacks --stack-name api-deployment-${tagName}
    doesStackExist=$?
    if [ "$doesStackExist" -eq 0 ]; then
      echo "stack exists going to delete it now"
      aws cloudformation delete-stack --stack-name api-deployment-${tagName} || exit 1
      aws cloudformation wait stack-delete-complete --stack-name api-deployment-${tagName} || exit 1
    else
      echo "stack does not exist yet"
    fi
}

function createNewAPIDeploymentStack() {
    cfn-create-or-update \
      --stack-name api-deployment-${tagName} \
      --template-body file://buildAndDeploy/deploy/api-ec2.yml \
      --parameters ParameterKey=tagName,ParameterValue=${tagName} \
                   ParameterKey=securityKeyName,ParameterValue=${securityKeyName} \
                   ParameterKey=commitHashOfDeployable,ParameterValue=${commitHashOfDeployable} \
      --wait || exit 1
}

createElasticIPAndSecurityGroup
deleteOldAPIDeploymentStackIfExists
createNewAPIDeploymentStack



