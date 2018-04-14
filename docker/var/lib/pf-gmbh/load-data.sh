#!/bin/bash

su postgres -c 'psql -f /var/lib/pf-gmbh/create-user.sql'
su postgres -c 'psql -f /var/lib/pf-gmbh/create-db.sql'
su postgres -c 'psql -d pfgmbh -f /var/lib/pf-gmbh/aenaflight_2017_01_dump_20180327_1125.sql'
