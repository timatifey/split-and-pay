//
//  PressEffectButtonStyle.swift
//  Split and pay
//
//  Created by Матвей Борисов on 14.11.2023.
//

import SwiftUI

struct PressEffectButtonStyle: ButtonStyle {
	func makeBody(configuration: Configuration) -> some View {
		configuration.label
			.scaleEffect(configuration.isPressed ? 0.99 : 1.0)
			.opacity(configuration.isPressed ? 0.8 : 1.0)
			.animation(.easeInOut, value: configuration.isPressed)
	}
}
