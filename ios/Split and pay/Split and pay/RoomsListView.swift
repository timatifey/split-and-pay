//
//  RoomsListView.swift
//  Split and pay
//
//  Created by Матвей Борисов on 15.11.2023.
//

import Foundation
import SwiftUI

struct RoomsListView: View {
	@EnvironmentObject var parentViewModel: MainViewModel


	var body: some View {
		VStack {
			Text("Комнаты")
				.onTapGesture {
					parentViewModel.clearUser()
				}
		}
		.padding()
	}
}

struct RoomsListView_Previews: PreviewProvider {
	static var previews: some View {
		RoomsListView()
	}
}
