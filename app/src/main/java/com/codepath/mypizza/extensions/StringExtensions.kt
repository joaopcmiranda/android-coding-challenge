package com.codepath.mypizza.extensions

import java.security.MessageDigest

fun String.sha512(): String {
    return this.hashWithAlgorithm("SHA-512")
}

fun String.sha256(): String {
    return this.hashWithAlgorithm("SHA-256")
}

fun String.md5(): String {
    return this.hashWithAlgorithm("MD5")
}

private fun String.hashWithAlgorithm(algorithm: String): String {
    val digest = MessageDigest.getInstance(algorithm)
    val bytes = digest.digest(this.toByteArray(Charsets.UTF_8))
    return bytes.fold("", { str, it -> str + "%02x".format(it) })
}