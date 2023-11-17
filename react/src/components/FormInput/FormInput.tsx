import "./style.css"

interface Props {
    className: any;
    text: string;
}

export const FormInput = ({className, text = "Email *"}: Props) => {
    return (
        <div className={`form-input ${className}`}>
            <div className="input-form-control">
                <div className="email">{text}</div>
            </div>
        </div>
    );
};