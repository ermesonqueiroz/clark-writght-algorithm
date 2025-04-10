package application.usecase

import application.Registry

abstract class UseCase {
    protected val registry = Registry.getInstance()
}