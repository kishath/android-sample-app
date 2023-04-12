package com.example.kishath.testutils

import fi.iki.elonen.NanoHTTPD
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean

class TestServer : NanoHTTPD(8080) {

    var shouldThrow = AtomicBoolean(false)

    init {
        start(SOCKET_READ_TIMEOUT, false)
    }

    override fun serve(session: IHTTPSession): Response {
        Thread.sleep(1000)
        if (!shouldThrow.get()) {
            return when {
                session.uri.endsWith("api/v2/en/countries") -> {
                    newFixedLengthResponse(
                        """{
                    "countries":[
                        {"displayName": "United Kingdom","isoCode": "UK"},
                        {"displayName": "Sri Lanka","isoCode": "LK"}
                    ]
                }""".trim()
                    )
                }
                else -> {
                    throw IOException("TestServer threw exception")
                }
            }
        } else {
            throw IOException("TestServer threw exception")
        }
    }
}