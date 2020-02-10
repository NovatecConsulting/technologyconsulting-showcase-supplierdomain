function setup
{
	# create suppliers
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_1.json http://$HOST:$PORT/supplierdomain/supplier
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_2.json http://$HOST:$PORT/supplierdomain/supplier
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_3.json http://$HOST:$PORT/supplierdomain/supplier

	# create supplier component
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_1_1.json http://$HOST:$PORT/supplierdomain/supplier/supplier_component
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_2_1.json http://$HOST:$PORT/supplierdomain/supplier/supplier_component
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_2_2.json http://$HOST:$PORT/supplierdomain/supplier/supplier_component
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_3_2.json http://$HOST:$PORT/supplierdomain/supplier/supplier_component
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/supplier_component_3_3.json http://$HOST:$PORT/supplierdomain/supplier/supplier_component
}

function business_calls
{
	# purchase components
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/component_demands.json http://$HOST:$PORT/supplierdomain/supplier/purchase
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST --data @data/component_demands_non_existing_supplier.json http://$HOST:$PORT/supplierdomain/supplier/purchase

	# deliver components
	curl -u admin:adminpwd --header "Content-Type: application/json" --request POST http://$HOST:$PORT/supplierdomain/supplier/process_delivery/1
}
