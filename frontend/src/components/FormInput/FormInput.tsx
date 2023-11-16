interface FormInput {
    className: string,
    text: String
}

export const FormInput = ({className, text = "Email *"}: FormInput) => {
    return (
        <div className={`form-input ${className}`}>
            <div className="input-form-control">
                <div className="email">{text}</div>
            </div>
        </div>
    );
};