(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn river-crossing-plan []
  (conj start-pos
        [[:fox :corn] [:you :boat :goose] []]
        [[:fox :corn] [:boat] [:you :goose]]
        [[:fox :corn] [:boat :you] [:goose]]
        [[:fox :corn :you] [:boat] [:goose]]
        [[:corn] [:fox :you :boat] [:goose]]
        [[:corn] [:boat] [:you :fox :goose]]
        [[:corn] [:you :goose :boat] [:fox]]
        [[:corn :goose :you] [:boat] [:fox]]
        [[:goose] [:you :corn :boat] [:fox]]
        [[:goose] [:boat] [:you :corn :fox]]
        [[:goose] [:you :boat] [:corn :fox]]
        [[:goose :you] [:boat] [:corn :fox]]
        [[] [:you :goose :boat] [:corn :fox]]
        [[] [:boat] [:you :goose :corn :fox]]))

(river-crossing-plan)
