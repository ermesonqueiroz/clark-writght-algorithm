package application

class Registry private constructor() {
    companion object {
        private var instance: Registry? = null

        fun getInstance(): Registry {
            if (instance == null) {
                instance = Registry()
            }

            return instance!!
        }
    }

    private var dependencies: HashMap<String, Any> = hashMapOf();

    fun provide(name: String, value: Any) {
        dependencies[name] = value
    }

    fun inject(name: String): Any? {
        return dependencies[name]
    }
}