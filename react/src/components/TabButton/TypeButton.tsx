interface TabButtonConfig {
    children: any,
    isActive: boolean,
    onClick: () => void
}

export default function TabButton({children, isActive, onClick}: TabButtonConfig) {
    if (isActive) {
        return (<b>{children}</b>)
    }
    return (
        <button onClick={() => {
            onClick();
        }}>
            {children}
        </button>
    )
}
