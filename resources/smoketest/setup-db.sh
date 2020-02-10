#!/bin/bash
declare HOST=localhost
declare PORT=9080

. `dirname $0`/options.sh
. `dirname $0`/supplierdomain.sh


function main_setup
{
	script_options

	# supplier setup runs only once!
	setup
}

main_setup $@
