(ns magic-square.puzzle)

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])
; (def values [1.0 1.5 2.0])

(defn squares [values]
  (for [x1 values
        x2 values
        x3 values
        :when (= (count (hash-set x1 x2 x3)) 3)
        y1 values
        y2 values
        y3 values
        :when (= (count (hash-set x1 x2 x3 y1 y2 y3)) 6)
        z1 values
        z2 values
        z3 values
        :when (= (count values) (count (hash-set x1 x2 x3 y1 y2 y3 z1 z2 z3)))]
    [[x1 x2 x3] [y1 y2 y3] [z1 z2 z3]]))

; (take 2 (squares values))
; (take 5 (squares values))
; (take 25 (squares values))

(defn magic? [[r1 r2 r3 :as square]]
  (apply =
    (reduce + (map first square))
    (reduce + (map second square))
    (reduce + (map last square))
    (+ (first r1) (second r2) (last r3))
    (+ (last r1) (second r2) (first r3))
    (map (partial reduce +) square)))


(defn magic-square [values]
  (->>
    values
    squares
    (filter magic?)
    first))

; (magic-square values)
