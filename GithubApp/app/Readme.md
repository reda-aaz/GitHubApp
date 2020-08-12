# Square Repos

This Android app shows a list of square repos in a recyclerview

## Libraries used

1. Retrofit: For network calls.
2. RxJava: To make asynchronous calls.
3. Junit: To do unit testing of our viewmodels.
4. Mockito: For mocking dependencies.
5. Espresso for UI testing.
6. Google Architecture components: Viewmodels and livedata.

This project takes advantage of databinding to more easily connect the viewmodel with the layout and decouple the view from the viewmodel.

I also implemented Idling resources for the ui tests due to the network calls being asynchronous there can be the case where the test ends before the network call, so with idling resources we can force the test to wait for the network call to finish up first and then we can evaluate.