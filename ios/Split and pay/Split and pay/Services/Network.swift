//
//  Network.swift
//  Split and pay
//
//  Created by Матвей Борисов on 15.11.2023.
//

import Foundation
import Combine

protocol APIEndpoint {
	var baseURL: URL { get }
	var path: String { get }
	var method: HTTPMethod { get }
	var headers: [String: String]? { get }
	var parameters: [String: Any]? { get }
}

enum HTTPMethod: String {
	case get = "GET"
	case post = "POST"
	case put = "PUT"
	case patch = "PATCH"
	case delete = "DELETE"
}

enum APIError: Error {
	case invalidResponse
	case invalidData
}

protocol APIClient {
	associatedtype EndpointType: APIEndpoint
	func request<T: Decodable>(_ endpoint: EndpointType) -> AnyPublisher<T, Error>
}

class URLSessionAPIClient<EndpointType: APIEndpoint>: APIClient {

	private let keychainProvider = KeychainProvider()

	func request<T: Decodable>(_ endpoint: EndpointType) -> AnyPublisher<T, Error> {
		let url = endpoint.baseURL.appendingPathComponent(endpoint.path)
		var request = URLRequest(url: url)
		request.httpMethod = endpoint.method.rawValue

		endpoint.headers?.forEach { request.addValue($0.value, forHTTPHeaderField: $0.key) }

		if let id = keychainProvider.obtainAuthToken() {
			request.addValue(id, forHTTPHeaderField: "userId")
		}

		if let parameters = endpoint.parameters {
			let jsonData = try? JSONSerialization.data(withJSONObject: parameters)
			request.httpBody = jsonData
		}

		return URLSession.shared.dataTaskPublisher(for: request)
			.subscribe(on: DispatchQueue.global(qos: .background))
			.tryMap { data, response -> Data in
				guard let httpResponse = response as? HTTPURLResponse,
					  (200...299).contains(httpResponse.statusCode) else {
					throw APIError.invalidResponse
				}
				return data
			}
			.decode(type: T.self, decoder: JSONDecoder())
			.eraseToAnyPublisher()
	}
}
