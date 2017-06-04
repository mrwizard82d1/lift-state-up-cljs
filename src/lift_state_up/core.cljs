(ns lift-state-up.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(println "This text is printed from src/lift-state-up/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:temperature 50}))

(om/root
  (fn [data owner]
    (reify om/IRender
      (render [_]
        (dom/p nil (if (>= (:temperature data) 100)
                     "The water would boil."
                     "The water would not boil.")))))
  app-state
  {:target (. js/document (getElementById "app"))})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
