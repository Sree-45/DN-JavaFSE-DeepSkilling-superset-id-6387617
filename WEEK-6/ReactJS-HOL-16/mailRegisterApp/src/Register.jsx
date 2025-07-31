import { useState } from 'react';
import './Register.css';

function Register() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: ''
    });

    const [errors, setErrors] = useState({
        name: '',
        email: '',
        password: ''
    });

    const [isFormValid, setIsFormValid] = useState(false);

    const validateName = (name) => {
        if (name.length < 5) {
            return 'Name should have at least 5 characters';
        }
        return '';
    };

    const validateEmail = (email) => {
        if (!email.includes('@') || !email.includes('.')) {
            return 'Email should contain @ and .';
        }
        return '';
    };

    const validatePassword = (password) => {
        if (password.length < 8) {
            return 'Password should have at least 8 characters';
        }
        return '';
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));

        let error = '';
        switch (name) {
            case 'name':
                error = validateName(value);
                break;
            case 'email':
                error = validateEmail(value);
                break;
            case 'password':
                error = validatePassword(value);
                break;
            default:
                break;
        }

        const newErrors = {
            ...errors,
            [name]: error
        };
        setErrors(newErrors);

        const isValid = !newErrors.name && !newErrors.email && !newErrors.password &&
                       formData.name && formData.email && formData.password;
        setIsFormValid(isValid);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        
        const nameError = validateName(formData.name);
        const emailError = validateEmail(formData.email);
        const passwordError = validatePassword(formData.password);

        const newErrors = {
            name: nameError,
            email: emailError,
            password: passwordError
        };

        setErrors(newErrors);

        if (!nameError && !emailError && !passwordError) {
            alert('Registration successful!');
            console.log('Form submitted:', formData);
            
            setFormData({
                name: '',
                email: '',
                password: ''
            });
            setErrors({
                name: '',
                email: '',
                password: ''
            });
            setIsFormValid(false);
        } else {
            alert('Please fix the validation errors before submitting.');
        }
    };

    return (
        <div className="register-container">
            <div className="register-form-wrapper">
                <h2 className="register-title">Register Here!!!</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="name" className="form-label">Name:</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            value={formData.name}
                            onChange={handleInputChange}
                            className="form-input"
                        />
                    </div>
                    {errors.name && <span className="error-message">{errors.name}</span>}

                    <div className="form-group">
                        <label htmlFor="email" className="form-label">Email:</label>
                        <input
                            type="text"
                            id="email"
                            name="email"
                            value={formData.email}
                            onChange={handleInputChange}
                            className="form-input"
                        />
                    </div>
                    {errors.email && <span className="error-message">{errors.email}</span>}

                    <div className="form-group">
                        <label htmlFor="password" className="form-label">Password:</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            value={formData.password}
                            onChange={handleInputChange}
                            className="form-input"
                        />
                    </div>
                    {errors.password && <span className="error-message">{errors.password}</span>}

                    <button type="submit" className="submit-button">
                        Submit
                    </button>
                </form>
            </div>
        </div>
    );
}

export default Register;