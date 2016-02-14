(ns keechma-login.controllers.logout
  (:require [keechma.controller :as controller]
            [keechma.app-state :as app-state]))

(defrecord Controller []
  controller/IController
  (params [_ route-params]
    (when (= (get-in route-params [:data :session-action]) "logout")
      true))
  (start [_ params app-db]
    (.log js/console (clj->js app-db))
    (set! (.-hash js/location) "")
    app-db)
  (stop [_ params app-db]
    (let [kv-store (:kv app-db)
          main-app (:main-app kv-store)]
      (app-state/stop! main-app)
      (assoc app-db :kv (dissoc kv-store :main-app)))))
