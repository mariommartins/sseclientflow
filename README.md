# SSEClientFlow

| Start of Event List  | End of Event List |
| ------------- | ------------- |
| <img src="https://github.com/mariommartins/sseclientflow/assets/109080050/3f41f43f-a095-4756-9b83-293dd069a160" width="250"> | <img src="https://github.com/mariommartins/sseclientflow/assets/109080050/5271b6e4-d9ce-4483-9ff3-49cc45cf620d" width="250"> |

## Technologies
This project was made in Kotlin using the following libs:
- **Hilt**
- **OkHttp_SSE**
- **Coroutines Flow**
- **Compose**
- Gson

## Functionality
This is a single-activity app that opens a SSE connection to a test Server and updates the Composable UI in real-time

## Architecture Notes
- This was built to be maintained for a while.
- This is a multi module project, focused on the Clean Architecture layers, which are structured as follows:
   <img src="https://github.com/mariommartins/sseclientflow/assets/109080050/a7f4448e-51f3-4767-ae45-58f23a4d9a8c" height="250">
- Domain is divided in two modules, **impl** and **public**. Where the impl has the final classes and the public has the exposed abstractions.
- The architecture used in the presentation layer was MVVM.
- There are some DesignPatterns being used inside the layers of the system, as UseCases, DataSources & Repositories.
- The code design and style was intended to be consistent and reasonable.

## More Details
- This was used as the sample project for a droidcon 2023 talk that can be checked at [this link](https://docs.google.com/presentation/d/1aZBKHHjuAkEYtO0UuAugR8ktuk07Bp8u2GelgjrK8BA/edit?usp=sharing)  
- There is a published article on the Medium platform discussing the theme of this Sample project at [this link](https://medium.com/@gutoomota/how-to-build-a-clean-sseclient-flow-69c623fcc9e2)  
- The Api used for testing in this implementation was found publicly in [this repository](https://github.com/manoamaro/another-hacker-news-client)

Please **contact me** if you have any questions!  
  Mário A. M. Martins  
  linkedin.com/in/mario-augusto/  
  h2mario@gmail.com  
