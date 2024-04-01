# Text Embeddings - MediaPipe with Android-Kotlin

An embedding is a relatively low-dimensional space into which you can translate high-dimensional vectors. Embeddings make it easier to do machine learning on large inputs like sparse vectors representing words. Ideally, an embedding captures some of the semantics of the input by placing semantically similar inputs close together in the embedding space. An embedding can be learned and reused across models.
[Embeddings Ref](https://developers.google.com/machine-learning/crash-course/embeddings/video-lecture)

Embeddings are commonly used for [OpenAI Ref](https://platform.openai.com/docs/guides/embeddings):
- Search
- Clustering (grouped by similarity)
- Recommendations
- Anomaly detection

[MediaPipe](https://developers.google.com/mediapipe) is a set of On-Device Machine Learning libraries ready for deployment in production. There are libraries for Android, iOS, Web, and Python. One of the multiple solutions is to create a numerical representation of text data, this means the embeddings.

This is an open-source code used MediaPipe for creating the embeddings and cosine as a similarity measure with Kotlin - Android.

Try it:

<img src="https://github.com/jggomez/AndroidMediaPipe/assets/661231/a894d4e6-eca3-490d-b71c-e10c47f18cf0" width="400" height="900">


Made with ❤ by  [jggomez](https://devhack.co).

[![Twitter Badge](https://img.shields.io/badge/-@jggomezt-1ca0f1?style=flat-square&labelColor=1ca0f1&logo=twitter&logoColor=white&link=https://twitter.com/jggomezt)](https://twitter.com/jggomezt)
[![Linkedin Badge](https://img.shields.io/badge/-jggomezt-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/jggomezt/)](https://www.linkedin.com/in/jggomezt/)
[![Medium Badge](https://img.shields.io/badge/-@jggomezt-03a57a?style=flat-square&labelColor=000000&logo=Medium&link=https://medium.com/@jggomezt)](https://medium.com/@jggomezt)

## License

    Copyright 2023 Juan Guillermo Gómez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
