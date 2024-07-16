#!/bin/bash
/usr/local/bin/deploy-helper block-traffic
if [ $? -ne 0 ]; then { echo "Failed, aborting." ; exit 1; } fi
