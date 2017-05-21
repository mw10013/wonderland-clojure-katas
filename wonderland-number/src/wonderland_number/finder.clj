(ns wonderland-number.finder)

(defn same-digits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(defn same-digits? [n & ns]
  (apply = (map (comp set str) (cons n ns))))

; (same-digits? 35 35)
; (same-digits? 35 35 35)
; (same-digits? 33 35 35)
; (same-digits? 33 35)
; (same-digits? 35)
; (same-digits?)
        

(defn wonder? [n]
  (and
    (= 6 (count (str n)))
    (same-digits? n (* 2 n))
    (same-digits? n (* 3 n))
    (same-digits? n (* 4 n))
    (same-digits? n (* 5 n))
    (same-digits? n (* 6 n))))
  

(defn wonderland-number []
  (first (filter wonder? (range))))

; (wonderland-number)
