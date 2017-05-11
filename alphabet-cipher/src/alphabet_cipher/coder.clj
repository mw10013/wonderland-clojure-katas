(ns alphabet-cipher.coder)

(def alpha [\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z])

(defn char-to-chart-row [c]
  (vec (take (count alpha) (drop-while (partial not= c) (cycle alpha)))))

(def chart (vec (map char-to-chart-row alpha)))

(defn char-to-index [c] (- (int c) (int \a)))
(defn index-to-alpha [idx] (nth alpha idx))

(defn encode [keyword message]
  (apply str (map #(nth (nth chart (char-to-index %2)) (char-to-index %1))
                  (cycle keyword) message)))

; (encode "scones" "meetmebythetree")

(defn decode-char [kc mc]
  (let [k-idx (char-to-index kc)
        indexed-rows (map-indexed vector chart)
        indexed-row (first (filter (fn [[idx row]] (= (nth row k-idx) mc)) indexed-rows))]
    (index-to-alpha (first indexed-row)))
  )

(defn decode [keyword message]
  (apply str (map decode-char (cycle keyword) message)))

(defn- decipher-chars [cc mc]
  (let [chart-row (nth chart (char-to-index mc))
        idx (ffirst (filter (fn [[_ c]] (= c cc)) (map-indexed vector chart-row)))]
    (index-to-alpha idx)))

(defn- match? [coll1 coll2]
  (not-any? false? (map = coll1 (cycle coll2))))

(defn- to-key [coll]
  (->> coll
       count
       range
       (map #(take (inc %) coll))
       (filter (partial match? coll))
       first))


(defn decipher [cipher message]
  (apply str (to-key (map decipher-chars cipher message))))

; (decipher "opkyfipmfmwcvqoklyhxywgeecpvhelzg" "thequickbrownfoxjumpsoveralazydog")
