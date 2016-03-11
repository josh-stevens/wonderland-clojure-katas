(ns wonderland-number.finder)

(defn hasAllTheSameDigits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(defn wonderland-number []
  (loop [x 100000]
    (if (hasAllTheSameDigits? x (* x 2))
      (if (hasAllTheSameDigits? x (* x 3))
        (if (hasAllTheSameDigits? x (* x 4))
          (if (hasAllTheSameDigits? x (* x 5))
            (if (hasAllTheSameDigits? x (* x 6))
              x
              (recur (inc x)))
            (recur (inc x)))
          (recur (inc x)))
        (recur (inc x)))
      (recur (inc x)))))

(defn get-digit [char]
  (Character/digit char 10))

(defn sum-cube-digits [num]
  (reduce #(+ %1 (* %2 %2 %2)) 0 (map get-digit (str num))))

(defn cube-sums []
  (loop [x 1
         acc []]
    (if (= x 1000)
      acc
      (recur (inc x) (if (= (sum-cube-digits x) x)
                       (conj acc x)
                       acc)))))



(map get-digit (str 242))
(sum-cube-digits 242)

(cube-sums)
(defn cube [x]
  (* x x x))
(cube 3)
(+ (cube 1) (cube 5) (cube 3))
