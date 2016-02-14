(ns keechma-login.components.trello
  (:require [keechma.ui-component :as ui]))

(defn render-current-user
  "Renders the user info based on the loaded data."
  [ctx current-user]
  [:div
   [:h1 (str "Hello " (:fullName current-user))]
   [:img {:src (str "https://trello-avatars.s3.amazonaws.com/" (:avatarHash current-user) "/30.png")}]
   [:br]
   [:a {:href (ui/url ctx {:session-action "logout"})} "Logout"]])

(defn render
  "Resolves the `:current-user` subscription if it's `nil` it shows the 
  \"Loading...\" string, otherwise it renders the current user info "
  [ctx]
  (fn []
    (let [current-user-sub (ui/subscription ctx :current-user)
          current-user @current-user-sub]
      (if current-user
        (render-current-user ctx current-user)
        [:div "Loading user info..."]))))

(def component (ui/constructor
                {:renderer render
                 :subscription-deps [:current-user]}))
