(ns keechma-login.controllers.login
  (:require [keechma.controller :as controller]
            [keechma.app-state :as app-state]))

(defn start-app!
  "Starts the main application when the user is suceessfully logged
  in."
  [main-app app-db-atom]
  (let [started-app (app-state/start! main-app false)]
    (swap! app-db-atom assoc-in [:kv :main-app] started-app)))

(defn login
  "Logs in the user."
  [main-app app-db-atom _]
  (let [success-cb (partial start-app main-app app-db-atom)]
    (.authorize js/Trello #js {:type "popup"
                               :name "Keechma Trello example"
                               :success success-cb})))

(defrecord ^{:doc "
Handles the user login.

When the user clicks the \"Login\" button it will use the Trello
client to create the session.

After that it will start the `main` application and save the
reference to it in the `session` app's application state.

This allows the `session` app to render the main application instead
of the login screen.
"} Controller [main-app]
  controller/IController
  (params [_ route-params] true)
  (handler [this app-db-atom in-chan _]
    (let [main-app (:main-app this)]
      (controller/dispatcher app-db-atom in-chan
                             {:login (partial login main-app)}))))
