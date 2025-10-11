
# Buzzwords and Bottles

Android application that scans labels of wine, beer or other stuff and creates or finds a description of the product


## Tech Used

**Client:** Kotlin, Android Studio, Google ML Kit, CameraX

The app uses fragments for all implementation which launches the descriptions list and camera allowing for neater and maintanable code. The descriptions page uses a RecyclerView for efficiency, scalability and customizability compared to alternatives.

CameraX is whats used for the camera due to its flexability and additional functionality if needed in the future. Google ML kit is a machine learning tool which is used to detect words on a button press from the camera and works well with CameraX.
## Lessons Learned

I learned more complex ideas within android studio such as CameraX and Googles ML Kit.
 I've learned more about sending data between fragments using SharedViewModels rather than bundles and more advanced Kotlin ideas such as interfaces through development of this app.

Roadblocks were overcome during development such as the FAB button not working which was noticed when the button was not outputting anything. Initally thinking that the function was at fault but later realising after debugging the button was setup incorrectly. What this taught me was to focus on simple, easily correctable possible reasons for bugs then move onto larger reasons for the bug to save time on more complex solutions.

I have gone through the motions of migrating from views to compose allowing me to experience and learn how to seamlessly migrate to a new framework. The migration was done to introduce Material 3 to the application and learning compose has shown me how intuitive and fun to use.


## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/dylan-champion/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://x.com/dialn4murder)

