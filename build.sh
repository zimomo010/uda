#!/bin/sh
git pull
mvn package
cp target/youtobe_data_analysis-1.0.1.jar /mnt/application/mo/youtobe_data_analysis/

exit