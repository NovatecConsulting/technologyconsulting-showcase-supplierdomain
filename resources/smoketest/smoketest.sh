curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_1.json http://localhost:9080/supplierdomain/supplier
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_2.json http://localhost:9080/supplierdomain/supplier
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_3.json http://localhost:9080/supplierdomain/supplier

curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_1_1.json http://localhost:9080/supplierdomain/supplier/supplier_component
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_2_1.json http://localhost:9080/supplierdomain/supplier/supplier_component
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_2_2.json http://localhost:9080/supplierdomain/supplier/supplier_component
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_3_2.json http://localhost:9080/supplierdomain/supplier/supplier_component
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_3_3.json http://localhost:9080/supplierdomain/supplier/supplier_component

curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/component_demands.json http://localhost:9080/supplierdomain/supplier/purchase
curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/component_demands_non_existing_supplier.json http://localhost:9080/supplierdomain/supplier/purchase

curl -u admin:adminpwd --header "Content-Type: application/json" --request POST http://localhost:9080/supplierdomain/supplier/process_delivery/1