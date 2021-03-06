Java Mash
=========

Mash is a wrapper for a deeply nested map of maps.  It simplifies navigating json structures, providing property like
 access to the data.

Building using maven
--------------------

    git clone http://github.com/coder36/mash.git
    cd mash
    mvn clean install
 
The mash-1.0.jar file will be built into the target folder.


Example
-------

The url: http://pure-savannah-9942.herokuapp.com/bill.json serves up

        {
          "statement": {
            "generated": "2015-01-11",
            "period": {
              "from": "2015-01-26",
              "to": "2015-02-25"
            }
          },
          "total": 136.03
        }

Read in the json, then wrap in Mash:

        Mash json = new Mash( (Map) new RestTemplate().getForObject( "http://pure-savannah-9942.herokuapp.com/bill.json", Map.class ));

Accessing properties is as simple as:

        double total = (Double) json.get( "total" );
        String period_from = json.get_s( "statement.period.from" );
        String period_to = json.get_s( "statement.period.to" );

        //or

        Mash period = (Mash) json.get( "statement.period" );
        period_to = (String) period.get("to");

* A missing property will yield an empty string.



Credit
------

The idea for Mash came from  [Hashie](https://github.com/intridea/hashie#mash) - a ruby gem.
