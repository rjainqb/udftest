add jar file:///Users/rjain/workspace/udftest/udftest/target/udftest-1.0-SNAPSHOT.jar;
add jar file:///Users/rjain/workspace/udftest/stringutils/target/stringutils-1.0-SNAPSHOT.jar;
create temporary function udftest as 'com.qubole.hive.udftest.UDFTest';
-- set hive.fetch.task.conversion=more;
-- set hive.plan.serialization.format=javaXML;

-- mapreduce execution
select udftest(l_comment) from lineitem1000rows limit 10;

