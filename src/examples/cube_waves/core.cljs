(ns examples.cube-waves.core
  (:require [zoetrope.core :as z]
            [zoetrope.IO.model :as model]
            [zoetrope.IO.window :as window]
            [zoetrope.IO.mouse :as mouse]
            [zoetrope.IO.dom :as dom]))

(enable-console-print!)

(defn circle []
  [:circle 
   (merge dom/svg-ns 
          {:attributes {:r 100} 
           :style      {:cx "500"
                        :cy "500"
                        :stroke-width 15
                        :fill "none"
                        :stroke "black"}})])
         

(defn svg []
  (dom/svg {:style {:position "absolute"
                    :width "100%" 
                    :height "100%"}}
           (circle)))
  
(defn root [inputs]
  ;{:dom (into [:div] (map (fn [x] [:h1 (str "number " x)]) (range 0 1000)))}
  {:dom (svg)
  ;{:dom [:div {:style {:color "red"}} [:h1 {:style {:margin-top "20%" :font-size 70}} "hello!"]]}
  ;{:dom [:div {:style {:color "red"}} [:h1 {:style {:margin-top "20%" :font-size 70}} "hello!"] [:h2 {:style {:color "blue"}} "World!"]]
  ;{:dom [:div "hello"] 
  ;{:dom [:div {:style {:width "20px" :height "20px" :background "red"}}] 
  ;{:dom [:div]
   :model {:x (get-in inputs [:window :dimensions :width]) :y 1}})

(z/run-io
  root 
  {:window {:dimensions window/dimensions}
   :mouse {:position (mouse/position)}
   :model (model/input {:x 0 :y 0})}
  {:dom (dom/renderer)
   :model model/output})
