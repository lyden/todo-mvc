#!/usr/bin/env bash

# Special handling for cloudbees setup
if [ "$BUILD_ENVIRONMENT" == "cloudbees" ]
then
	# Install node into Cloudbees node - https://developer.cloudbees.com/bin/view/DEV/Node+Builds
	curl -s -o use-node https://repository-cloudbees.forge.cloudbees.com/distributions/ci-addons/node/use-node
	NODE_VERSION=0.10.21 . ./use-node
fi

# More special handling for ec2
if [ "$BUILD_ENVIRONMENT" == "ec2" ]
then
	PATH=$PATH:/usr/local/bin:/usr/local/sbin
fi

# Verify that npm is available, then execute
if hash npm 2>/dev/null; then
	npm "$@"
else
	echo "Error: Unable to find npm on path" 1>&2
	exit 1
fi
