# how to set a set of brokers for scdf item
--properties "app.*.spring.cloud.stream.kafka.binder.brokers=foo0.broker.foo,foo1.broker.foo,foo2.broker.foo"




# create an scdf service with a default set of brokers
cf create-service p-dataflow standard data-flow -c '{"messaging-data-service": { "user-provided": {"brokers":"10.0.1.15:9093,10.0.1.17:9093,10.0.1.18:9093"}}}'

# bind credhub to scdf
cf update-service data-flow -c '{"services":["scdf-credhub"]}'

# load data into credhub
cf update-service data-flow -c json.data  
the certs that are loaded into credhub are base64 encoded!

# set some properties some env vars for your scdf application
deployer.\<app>.cloudfoundry.env  
if my application is named kafka-ssl-test and deployed in cf 

spring.cloud.dataflow.skipper.platformName=default
deployer.kafka-ssl-test.cloudfoundry.env.CREDHUB="scdf-credhub"
deployer.kafka-ssl-test.cloudfoundry.env.JAVA_OPT=-XX:MaxDirectMemorySize=512M -Dlogging.level.org.springframework=TRACE
deployer.kafka-ssl-test.cloudfoundry.env.JBP_CONFIG_SPRING_AUTO_RECONFIGURATION={enabled: false}
deployer.kafka-ssl-test.cloudfoundry.env.KAFKA_CLIENTSTORE="keystore"
deployer.kafka-ssl-test.cloudfoundry.env.KAFKA_TRUSTSTORE="truststore"
deployer.kafka-ssl-test.cloudfoundry.env.SPRING_PROFILES_ACTIVE=cloud


kafka-ssl-testing

# send a test msg

curl -X POST -F 'message=test'  https://<endpoint>/kafka/publish -v