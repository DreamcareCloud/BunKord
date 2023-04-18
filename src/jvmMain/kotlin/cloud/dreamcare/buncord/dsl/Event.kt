package cloud.dreamcare.buncord.dsl

import cloud.dreamcare.buncord.kord
import dev.kord.core.event.Event
import dev.kord.core.on
import kotlinx.coroutines.Job

public inline fun <reified T: Event> event(noinline consumer: suspend T.() -> Unit): Job {
    return kord.on<T>(kord, consumer)
}
