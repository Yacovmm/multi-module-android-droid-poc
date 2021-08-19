package libraries.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface ICoroutinesDispatchers {
    val io_thread: CoroutineDispatcher
    val ui_thread: CoroutineDispatcher
    val default_thread: CoroutineDispatcher
}

class CoroutinesDispatchers : ICoroutinesDispatchers {
    override val io_thread: CoroutineDispatcher
        get() = Dispatchers.IO
    override val ui_thread: CoroutineDispatcher
        get() = Dispatchers.Main
    override val default_thread: CoroutineDispatcher
        get() = Dispatchers.Default
}
