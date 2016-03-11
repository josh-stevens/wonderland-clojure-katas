(ns alphabet-cipher.coder)

(declare gen-alphabet pick-letter-curried shortest replace? get-offset)

(defn encode [keyword message]
  "encodeme"
  (let [cipher-table (map gen-alphabet (seq (take (count message) (cycle (seq keyword)))))
        pick-letter (partial pick-letter-curried :encode message)]
    (apply str (map-indexed pick-letter cipher-table))))

(defn decode [keyword message]
  "decodeme"
  (let [cipher-table (map gen-alphabet (seq (take (count message) (cycle (seq keyword)))))
        pick-letter (partial pick-letter-curried :decode message)]
    (apply str (map-indexed pick-letter cipher-table))))

(defn decipher [cipher message]
  "decypherme"
  (let [cipher-table (map gen-alphabet (seq message))
        pick-letter (partial pick-letter-curried :decode cipher)]
    (shortest (apply str (map-indexed pick-letter cipher-table)))))

;; helper functions ---------------------------------------------------
;; --------------------------------------------------------------------

;; generate entire alphabet, starting at a given character and wrapping around z -> a
(defn gen-alphabet [start]
  (loop [letter (first (char-array (clojure.string/lower-case start)))
         wrapped false
         alphabet []]
    (if (= wrapped false)
      (if (= letter \z)
        (recur \a true (conj alphabet letter))
        (recur (char (+ (int letter) 1)) wrapped (conj alphabet letter)))
      (if (= letter (first (char-array (clojure.string/lower-case start))))
        alphabet
        (recur (char (+ (int letter) 1)) wrapped (conj alphabet letter)))
    )))

;; this fn takes mode and message as partials, and then is passed as callback to 'map-indexed'
;; which applies to a collection and takes idx, item as args
(defn pick-letter-curried [mode message idx item]
  (let [alphabet (gen-alphabet \a)]
    (cond (= mode :encode) (get item (.indexOf alphabet (get message idx)))
          (= mode :decode) (get alphabet (.indexOf item (get message idx))))))

;; this fn will take a string with repetitions in it and find the shortest substring that, when
;; repeated to the length of the string, will generate the entire string
(defn shortest [s]
  (loop [i 1]
      (let [tmp (apply str (take i s))]
      (when (< i (count s))
        (if (replace? s tmp)
          (str tmp)
          (recur (inc i)))))))

;; can s1 be made entirely from repeats of s2?
(defn replace? [s1 s2]
  (loop [i 0]
    (if (< i (count s1))
      (if (= (get-offset s1 i) (get-offset s2 i))
        (recur (inc i))
        false)
      true)))

;; get the item at index "offset" in arr, or if offset is out of bounds, wrap around the string
(defn get-offset [arr offset]
  (if (get arr offset)
    (get arr offset)
    (get-offset arr (- offset (count arr)))))
