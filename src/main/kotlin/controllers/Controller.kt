package controllers

import application.Registry
import com.sun.net.httpserver.HttpHandler

abstract class Controller : HttpHandler {
    protected val registry = Registry.getInstance()
}