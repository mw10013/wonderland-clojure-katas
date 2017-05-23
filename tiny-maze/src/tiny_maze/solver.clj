(ns tiny-maze.solver)

(defn walk [coll]
  (when-first [[m r c] coll]
    (let [v (get-in m [r c])]
      (cond
        (= v :E) (assoc-in m [r c] :x)
        (#{:S 0} v) (let [n (assoc-in m [r c] :x)]
                      (recur (concat (map (fn [[rx cx]] [n (+ r rx) (+ c cx)]) [[-1 0] [1 0] [0 1] [-1 0]]) (rest coll))))
        :else (recur (rest coll))))))
    

(defn solve-maze [maze]
  (walk [[maze 0 0]]))

; (solve-maze [[:S 0 1] [1 0 1] [1 0 :E]])
; (solve-maze [[:E 0 1] [1 0 1] [1 0 :E]])

; (get-in [[:S 0 1] [1 0 1] [1 0 :E]] [2 2])
; (get-in [[:S 0 1] [1 0 1] [1 0 :E]] [0 1])
; (get-in [[:S 0 1] [1 0 1] [1 0 :E]] [0 -1])

