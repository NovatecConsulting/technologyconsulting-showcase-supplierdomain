#!/bin/bash

java -Dmockserver.initializationJsonPath=./data/init_expectations.json -jar ./lib/mockserver-netty-5.8.1-jar-with-dependencies.jar -serverPort 9090 -logLevel ERROR &
#wait while mockserver is staring
sleep 3

# purchase components
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/component_demands.json http://localhost:9080/supplierdomain/supplier/purchase
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/component_demands_non_existing_supplier.json http://localhost:9080/supplierdomain/supplier/purchase

# deliver components
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST http://localhost:9080/supplierdomain/supplier/process_delivery/1

curl -X PUT "http://localhost:9090/stop" -H  "accept: */*"