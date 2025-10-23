-- ==============================================
-- CLEANUP (orden según dependencias)
-- ==============================================
-- Hijas
DELETE FROM recipe_ingredients;
DELETE FROM allergy_ingredients;
DELETE FROM recommendations;
DELETE FROM meal_plans;
DELETE FROM nutritionists;
DELETE FROM user_roles;
DELETE FROM users;
-- Join table entre user_profiles y allergies: debe borrarse antes de allergies
DELETE FROM user_profile_allergies;

-- Intermedias / que dependen de catálogos
DELETE FROM recipes;

-- Catálogos
DELETE FROM ingredients;
DELETE FROM allergies;
DELETE FROM recommendation_templates;
DELETE FROM categories;
DELETE FROM recipe_types;
DELETE FROM objectives;
DELETE FROM activity_levels;

-- ==============================================
-- SEED DE CATEGORIES
-- ==============================================
INSERT INTO categories (id, name) VALUES
                                      (1,'Desayuno'),
                                      (2,'Almuerzo'),
                                      (3,'Cena'),
                                      (4,'Snack'),
                                      (5,'Postre');

-- ==============================================
-- SEED DE RECIPE_TYPES
-- ==============================================
INSERT INTO recipe_types (id, name) VALUES
                                        (1,'Postres'),
                                        (2,'Vegana'),
                                        (3,'Omnívora'),
                                        (4,'Vegetariana'),
                                        (5,'Paleo');

-- ==============================================
-- SEED DE OBJECTIVES
-- ==============================================
INSERT INTO objectives (id, objective_name, score) VALUES
                                                       (1, 'ganancia', 1),
                                                       (2, 'perdida', 2),
                                                       (3, 'mantenimiento', 3);

-- ==============================================
-- SEED DE INGREDIENTS
-- ==============================================
INSERT INTO ingredients (id, name, calories, carbohydrates, proteins, fats, macronutrient_values_id, created_at, updated_at) VALUES
                                                                                                                                 (1, 'nueces', 654.0, 21.0, 15.1, 6.0, 1, NOW(), NOW()),
                                                                                                                                 (2, 'tomate', 130.0, 3.5, 1.0, 0.5, 2, NOW(), NOW()),
                                                                                                                                 (3, 'cebolla', 40.0, 9.0, 1.1, 0.0, 3, NOW(), NOW()),
                                                                                                                                 (4, 'Pecanas', 691.0, 14.0, 9.2, 72.0, 3, NOW(), NOW()),
                                                                                                                                 (5, 'Leche', 61.0, 4.8, 3.2, 3.3, 3, NOW(), NOW()),
                                                                                                                                 (6, 'Queso', 402.0, 1.3, 25.0, 33.0, 3, NOW(), NOW());

-- ==============================================
-- SEED DE ALLERGIES
-- ==============================================
INSERT INTO allergies (id, name) VALUES
                                     (1, 'Alergia a las nueces'),
                                     (2, 'Intolerancia a la lactosa');

-- ==============================================
-- SEED DE ALLERGY_INGREDIENTS
-- ==============================================
INSERT INTO allergy_ingredients (allergy_id, ingredient_name) VALUES
                                                                  (1, 'nueces'),
                                                                  (1, 'Pecanas'),
                                                                  (2, 'Leche'),
                                                                  (2, 'Queso');

-- ==============================================
-- SEED DE ACTIVITY LEVELS
-- ==============================================
INSERT INTO activity_levels (id, created_at, updated_at, name, description, activity_factor) VALUES
                                                                                                 (1, NOW(), NOW(), 'Sedentario', 'Poco o ningún ejercicio', 1.2),
                                                                                                 (2, NOW(), NOW(), 'Baja actividad', 'Ejercicio ligero/deporte 1 a 3 días por semana', 1.375),
                                                                                                 (3, NOW(), NOW(), 'Moderada actividad', 'Ejercicio moderado/deporte 3 a 5 días por semana', 1.55),
                                                                                                 (4, NOW(), NOW(), 'Regular activo', 'Ejercicio intenso/deporte 6 a 7 días por semana', 1.725),
                                                                                                 (5, NOW(), NOW(), 'Muy activo', 'Ejercicio muy intenso/trabajo físico o entrenamiento doble', 1.9);

-- ==============================================
-- SEED DE RECOMMENDATION TEMPLATES
-- ==============================================
INSERT INTO recommendation_templates (id, title, content, category) VALUES
                                                                        (1, 'Beber más agua', 'Recuerda beber al menos 8 vasos de agua al día para mantenerte hidratado.', 'Salud General'),
                                                                        (2, 'Ejercicio regular', 'Realiza al menos 30 minutos de actividad física moderada, cinco días a la semana.', 'Actividad Física'),
                                                                        (3, 'Más verduras', 'Incluye al menos una porción de verduras en cada comida.', 'Nutrición'),
                                                                        (4, 'Menos comida ultraprocesada', 'Reduce el consumo de alimentos ultraprocesados para mejorar tu salud.', 'Nutrición'),
                                                                        (5, 'Dormir bien', 'Procura dormir entre 7 y 8 horas cada noche para una recuperación adecuada.', 'Salud General');

-- ==============================================
-- SEED DE RECOMMENDATIONS
-- ==============================================
INSERT INTO recommendations (id, user_id, template_id, reason, notes, time_of_day, score, status, assigned_at, created_at, updated_at) VALUES
                                                                                                                                           (1, NULL, 1, 'Baja hidratación detectada en el perfil.', 'Se recomienda aumentar el consumo de agua.', 'MORNING', 9.5, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (2, NULL, 2, 'Reporta poco movimiento semanal.', 'Implementar rutina de caminatas diarias.', 'AFTERNOON', 8.0, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (3, NULL, 3, 'Consumo bajo de micronutrientes.', 'Agregar verduras variadas a cada comida.', 'EVENING', 7.0, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (4, NULL, 4, 'Ingesta elevada de ultraprocesados.', 'Reducir la cantidad de snacks ultraprocesados.', 'EVENING', 6.5, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (5, NULL, 5, 'Indicios de fatiga y sueño irregular.', 'Ajustar horario y condiciones para dormir mejor.', 'EVENING', 9.0, 'ACTIVE', NULL, NOW(), NOW());

-- ==============================================
-- SEED DE RECIPES
-- ==============================================
INSERT INTO recipes (id, user_id, name, description, preparation_time, difficulty, category_id, recipe_type_id, created_at, updated_at) VALUES
                                                                                                                                            (1, 1, 'Avena con Frutas', 'Avena cocida con plátano, fresas y miel.', 10, 'Fácil', 1, 4, NOW(), NOW()),
                                                                                                                                            (2, 1, 'Omelette de Vegetales', 'Tortilla con cebolla y tomate.', 15, 'Fácil', 1, 3, NOW(), NOW()),
                                                                                                                                            (3, 1, 'Smoothie Bowl Verde', 'Smoothie de espinaca, plátano y granola.', 8, 'Fácil', 1, 2, NOW(), NOW()),
                                                                                                                                            (4, 1, 'Pancakes Proteicos', 'Pancakes de avena y clara de huevo.', 20, 'Media', 1, 3, NOW(), NOW()),
                                                                                                                                            (5, 1, 'Tostadas Integrales con Aguacate', 'Pan integral con aguacate y tomate.', 10, 'Fácil', 1, 4, NOW(), NOW()),
                                                                                                                                            (6, 1, 'Pollo a la Plancha con Quinoa', 'Pollo asado con quinoa.', 30, 'Media', 2, 3, NOW(), NOW()),
                                                                                                                                            (7, 1, 'Ensalada César con Pollo', 'Pollo grillado con aderezo césar.', 20, 'Fácil', 2, 3, NOW(), NOW()),
                                                                                                                                            (8, 1, 'Pasta Integral con Vegetales', 'Pasta integral con tomate y albahaca.', 25, 'Media', 2, 4, NOW(), NOW()),
                                                                                                                                            (9, 1, 'Bowl Vegetariano', 'Arroz integral con garbanzos.', 25, 'Media', 2, 4, NOW(), NOW()),
                                                                                                                                            (10, 1, 'Salmón al Horno con Brócoli', 'Salmón con brócoli al vapor.', 35, 'Media', 2, 3, NOW(), NOW()),
                                                                                                                                            (11, 1, 'Sopa de Lentejas', 'Lentejas con zanahoria y apio.', 40, 'Fácil', 3, 4, NOW(), NOW()),
                                                                                                                                            (12, 1, 'Tacos de Pollo', 'Tacos con pollo y aguacate.', 25, 'Media', 3, 3, NOW(), NOW()),
                                                                                                                                            (13, 1, 'Ensalada Caprese', 'Tomate, mozzarella y albahaca.', 10, 'Fácil', 3, 4, NOW(), NOW()),
                                                                                                                                            (14, 1, 'Wrap de Atún', 'Atún con pepino y yogurt griego.', 15, 'Fácil', 3, 3, NOW(), NOW()),
                                                                                                                                            (15, 1, 'Tortilla Española Light', 'Tortilla de papas y cebolla.', 30, 'Media', 3, 3, NOW(), NOW()),
                                                                                                                                            (16, 1, 'Hummus con Vegetales', 'Hummus con palitos de zanahoria.', 10, 'Fácil', 4, 2, NOW(), NOW()),
                                                                                                                                            (17, 1, 'Yogurt Griego con Nueces', 'Yogurt con nueces y miel.', 5, 'Fácil', 4, 4, NOW(), NOW()),
                                                                                                                                            (18, 1, 'Batido de Proteína', 'Proteína con leche de almendras.', 5, 'Fácil', 4, 3, NOW(), NOW()),
                                                                                                                                            (19, 1, 'Frutas con Mantequilla de Maní', 'Fruta con mantequilla de maní.', 5, 'Fácil', 4, 4, NOW(), NOW()),
                                                                                                                                            (20, 1, 'Energy Balls', 'Bolitas de dátiles y almendras.', 15, 'Fácil', 4, 2, NOW(), NOW()),
                                                                                                                                            (21, 1, 'Brownie Saludable', 'Brownie con harina de almendra.', 30, 'Media', 5, 1, NOW(), NOW()),
                                                                                                                                            (22, 1, 'Helado de Plátano', 'Helado de plátano y cacao.', 5, 'Fácil', 5, 2, NOW(), NOW()),
                                                                                                                                            (23, 1, 'Pudín de Chía', 'Chía con leche de coco.', 10, 'Fácil', 5, 2, NOW(), NOW()),
                                                                                                                                            (24, 1, 'Muffins de Arándanos', 'Muffins integrales con avena.', 25, 'Media', 5, 4, NOW(), NOW()),
                                                                                                                                            (25, 1, 'Galletas de Avena', 'Galletas crujientes con pasas.', 20, 'Fácil', 5, 4, NOW(), NOW());

-- ==============================================
-- SEED DE RECIPE_INGREDIENTS (Relaciones válidas)
-- ==============================================
INSERT INTO recipe_ingredients (recipe_id, ingredient_id) VALUES
                                                              (17, 1), (17, 5),  -- Yogurt Griego con Nueces
                                                              (16, 2), (16, 3),  -- Hummus con Vegetales
                                                              (13, 2), (13, 6),  -- Ensalada Caprese
                                                              (20, 1), (20, 4),  -- Energy Balls
                                                              (21, 1), (21, 5),  -- Brownie Saludable
                                                              (10, 2), (11, 3), (12, 2), (12, 3), (14, 2), (15, 3);  -- Otros

-- ==============================================
-- SEED DE USERS Y NUTRITIONISTS
-- ==============================================
-- Crear usuarios base primero
INSERT INTO users (id, username, password, created_at, updated_at)
VALUES
    (101, 'valeria', '$2a$10$abc...', NOW(), NOW()),
    (102, 'javier', '$2a$10$abc...', NOW(), NOW()),
    (103, 'sofia',  '$2a$10$abc...', NOW(), NOW()),
    (104, 'martin', '$2a$10$abc...', NOW(), NOW());

-- Asignar roles de nutricionista
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE r.name = 'ROLE_NUTRITIONIST'
  AND u.username IN ('valeria', 'javier', 'sofia', 'martin');

-- Crear nutricionistas
INSERT INTO nutritionists
(user_id, full_name, license_number, specialty, years_experience, accepting_new_patients, bio, profile_picture_url, created_at, updated_at)
VALUES
    (101, 'Dra. Valeria Castro', 'CMP-12345', 'CLINICAL', 8, true,
     'Nutricionista clínica especializada en hábitos saludables y control de peso.',
     'https://cdn.fitapp.com/nutritionists/castro.jpg', NOW(), NOW()),
    (102, 'Lic. Javier Torres', 'CMP-44567', 'SPORTS', 6, true,
     'Nutricionista deportivo con experiencia en alto rendimiento y fitness.',
     'https://cdn.fitapp.com/nutritionists/torres.jpg', NOW(), NOW()),
    (103, 'Dra. Sofía Gutiérrez', 'CMP-23451', 'PEDIATRIC', 10, true,
     'Especialista en nutrición infantil y dietas balanceadas para niños y adolescentes.',
     'https://cdn.fitapp.com/nutritionists/gutierrez.jpg', NOW(), NOW()),
    (104, 'Lic. Martín Vargas', 'CMP-56789', 'RENAL', 7, false,
     'Nutricionista enfocado en dietas terapéuticas para pacientes con enfermedades renales.',
     'https://cdn.fitapp.com/nutritionists/vargas.jpg', NOW(), NOW());

-- ==============================================
-- RESET SEQUENCES
-- ==============================================
SELECT setval('recipes_id_seq', (SELECT MAX(id) FROM recipes) + 1);
SELECT setval('recommendations_id_seq', (SELECT MAX(id) FROM recommendations) + 1);
SELECT setval('recommendation_templates_id_seq', (SELECT MAX(id) FROM recommendation_templates) + 1);
