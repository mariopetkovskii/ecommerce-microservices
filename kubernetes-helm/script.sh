helm upgrade --install mailhog mailhog-chart
helm upgrade --install zookeeper zookeeper-chart
helm upgrade --install kafka kafka-chart
helm upgrade --install zipkin zipkin-chart
helm upgrade --install service-discovery service-discovery-chart
helm upgrade --install gateway gateway-chart
helm upgrade --install product-postgres product-postgres-chart
#helm upgrade --install product product-chart
helm upgrade --install prometheus prometheus-chart
helm upgrade --install users-postgres users-postgres-chart
#helm upgrade --install users users-chart
helm upgrade --install shopping-cart-postgres shopping-cart-postgres-chart
#helm upgrade --install shopping-cart shopping-cart-chart
helm upgrade --install order-postgres order-postgres-chart
#helm upgrade --install order order-chart
#helm upgrade --install notification notification-chart