package viewModels

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import components.loggedComponents.Heading

class LoggedInViewModel: ScreenModel {
    var selectedHeading by mutableStateOf(Heading.HEADING1)
        private set

    val scrollState = LazyListState()

    val showTopRow by derivedStateOf {
            scrollState.firstVisibleItemIndex > 0 ||
                    (scrollState.firstVisibleItemIndex > 1
                            && scrollState.firstVisibleItemScrollOffset > 0)
        }

    fun weSetSelectedHeading(heading: Heading) {
        selectedHeading = heading
    }

}