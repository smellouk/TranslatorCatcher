package io.mellouk.translatorcatcher.utils

import io.mellouk.translatorcatcher.base.BaseCommand

interface Commandable<Cmd : BaseCommand> {
    fun onCommand(cmd: Cmd)
}