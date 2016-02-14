(ns keechma-login.subscriptions
  (:require-macros [reagent.ratom :refer [reaction]]))

(defn current-user [app-db]
  ;; Returns the current user. Called by the `main` app
  (reaction
   (get-in @app-db [:kv :current-user])))

(defn main-app [app-db]
  ;; Returns the main application. Called by the `session` app
  (reaction
   (get-in @app-db [:kv :main-app])))
