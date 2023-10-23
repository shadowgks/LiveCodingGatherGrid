function deleteAlert(button) {
    let alertDiv = button.parentNode;
    alertDiv.parentNode.removeChild(alertDiv);
}