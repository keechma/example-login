(ns keechma-login.controllers.trello
  (:require [keechma.controller :as controller]))

(defn load-user-info [app-db-atom]
  (.get (.-members js/Trello) "me"
        (fn [user-info]
          (swap! app-db-atom assoc-in [:kv :current-user] (js->clj user-info :keywordize-keys true)))))

(defrecord Controller []
  controller/IController
  (params [_ _] true)
  (handler [_ app-db-atom _ _]
    (load-user-info app-db-atom)))
