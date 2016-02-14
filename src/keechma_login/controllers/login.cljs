(ns keechma-login.controllers.login
  (:require [keechma.controller :as controller]
            [keechma.app-state :as app-state]))

(defn start-app [main-app app-db-atom]
  (let [started-app (app-state/start! main-app false)]
    (swap! app-db-atom assoc-in [:kv :main-app] started-app)))

(defn login [main-app app-db-atom _]
  (let [success-cb (partial start-app main-app app-db-atom)]
    (.authorize js/Trello #js {:type "popup"
                               :name "Keechma Trello example"
                               :success success-cb})))

(defrecord Controller [main-app]
  controller/IController
  (params [_ route-params] true)
  (handler [this app-db-atom in-chan _]
    (let [main-app (:main-app this)]
      (controller/dispatcher app-db-atom in-chan
                             {:login (partial login main-app)}))))
