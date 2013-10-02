This repository contains an example of how to configure a Camel route that can
read from an Oracle AQ queue and write to an Oracle database table in a single
transaction.

This example uses Atomikos as the transaction manager; the key to making this
work is proxying both the JMS connection factory and the JDBC data source
with Atomikos classes, which takes care of enlisting the resources in a 
transaction.

This code assumes a schema with a queue and table to work - I will add the DDL
at some point.

To build the sample, you'll need to add the oracle AQ jar and the oracle jdbc 
jar to your maven repository, as these do not seem to be available via a 
public repository.

You will also need to make sure Oracle is configured to support XA transactions,
and that your database user (e.g. batch) has execute privileges on the
sys.dbms_system package.

If you are using Oracle XA, there is [additional configuration](http://www.atpeaz.com/index.php/2010/fixing-the-ora-12519-tnsno-appropriate-service-handler-found-error/) needed to avoid errors.

Finally, you'll need plenty of head room when you run the sample, so a JVM 
option of -Xmx2014m would be appropriate.
