# Cryptic_Wallet-KMP-App

Cryptic Wallet is a cross-platform cryptocurrency wallet app designed to securely manage multiple coins. It mimics the functionality of Trust Wallet, providing users with the ability to create multi-coin wallets, send and receive assets, and interact with QR codes for easy address management. Built using **Kotlin Multiplatform**, the app runs seamlessly on both Android and iOS.

## Features

- **Multi-Coin Wallet**: Create and manage multiple cryptocurrency wallets within the app.
- **Send and Receive Assets**: Easily transfer crypto assets to and from other wallets.
- **QR Code Integration**: 
  - Scan QR codes to auto-fill the recipient’s wallet address when sending assets.
  - Generate a QR code for your own wallet address to easily share with others.
- **Cross-Platform Support**: Built using Kotlin Multiplatform, ensuring a native experience on both Android and iOS.
- **Swift Integration**: A small amount of Swift code is used to ensure smooth iOS functionality.

## Tech Stack

### Language
- **Kotlin** (primary language)
- **Swift** (used minimally for iOS integration)

### Libraries/Frameworks
- **[Navigator](https://github.com/some-navigator-link)**: For smooth in-app navigation.
- **[TrustWalletCore](https://github.com/trustwallet/wallet-core)**: Core library for multi-coin wallet management and crypto transactions.
- **[Material3](https://material.io/design)**: UI/UX library for consistent and modern design.
- **[Ktor](https://ktor.io/)**: Networking library used for backend API interactions.
- **[Kamel Image](https://github.com/kamel-image/kamel)**: Image handling for caching and loading wallet logos.
- **[Koin Dependency Injection](https://insert-koin.io/)**: For dependency injection and managing app-wide dependencies.
- **[SplashScreen](https://developer.android.com/guide/topics/ui/splash-screen)**: Provides a polished splash screen experience across platforms.

## Project Structure

- **Compose App**: The entire project is structured inside the `compose_app` directory.
- **Shared Code**: Shared Kotlin Multiplatform code is located in `wallet.kt`. Here, you'll find the `expect` and `actual` functions, handling platform-specific logic for both Android and iOS.
  
## Setting Up the Project Locally

To run the Cryptic Wallet app locally, follow these steps:

### Prerequisites
- **JDK 11+**: Ensure that you have Java Development Kit (JDK) version 11 or higher installed.
- **Android Studio**: Latest stable version for Android development.
- **Xcode**: For building and running the app on iOS devices/simulators.
- **Kotlin Multiplatform Setup**: Ensure that your development environment is set up for Kotlin Multiplatform.

### Installation Steps

1. **Clone the Repository**:
  

2. **Android Setup**:
   - Open the project in **Android Studio**.
   - Sync Gradle by clicking on "Sync Now" when prompted.
   - Build and run the project on an Android emulator or a physical device.

3. **iOS Setup**:
   - Open `iosApp` in **Xcode**.
   - Run the project on an iOS simulator or a physical device.

4. **Build the App**:
   - Use the Kotlin Multiplatform capabilities to build for both platforms with the command:
     ```
     ./gradlew build
     ```

5. **Run Tests** (optional):
   - Ensure that everything is working as expected by running the available unit and integration tests:
     ```
     ./gradlew test
     ```

## QR Code Functionality

- **Scan a QR Code**: Use the in-app scanner to scan a recipient's wallet address when transferring crypto assets.
- **Generate a QR Code**: Easily generate a QR code from your wallet address to share with others.

## Contributing

We welcome contributions to Cryptic Wallet! Please follow these guidelines for contributing:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, feel free to reach out to me
