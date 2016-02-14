(ns keechma-login.subscriptions
  (:require-macros [reagent.ratom :refer [reaction]]))

(defn current-user [app-db]
  (reaction
   (get-in @app-db [:kv :current-user])))

(defn main-app [app-db]
  (reaction
   (get-in @app-db [:kv :main-app])))
