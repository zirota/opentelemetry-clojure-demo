(ns server
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [clojure.pprint] 
            [clojure.tools.logging :as log]))

(defn handler [request]
  (clojure.pprint/pprint request)
  (log/info "Someone made a request")
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defn -main
  [& args]
  (jetty/run-jetty handler
                   {:port 3000
                    :join? true}))

(comment 
  (compile 'server)

  ;; To start server via java
  ;; java -javaagent:${PATH_TO_OPENTELEMETRY_JAR} \
  ;;    -Dclojure.tools.logging.factory=clojure.tools.logging.impl/log4j2-factory \
  ;;    -Dlog4j2.configurationFile=/mnt/c/Users/Hazzen/clojure-projects/opentelemetry-starting-out/log4j2.xml \
  ;;    -Dotel.service.name=simple-server \
  ;;    -Dotel.traces.exporter=logging \
  ;;    -Dotel.logs.exporter=logging \
  ;;    -cp `clj -Spath` server 

  )
