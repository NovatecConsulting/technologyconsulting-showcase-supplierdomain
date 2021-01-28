--
-- PostgreSQL database dump
--

-- Dumped from database version 11.9 (Debian 11.9-1.pgdg90+1)
-- Dumped by pg_dump version 11.9 (Debian 11.9-1.pgdg90+1)


--
-- Data for Name: sequence; Type: TABLE DATA; Schema: public; Owner: supplier_user
--

--COPY public.sequence (seq_name, seq_count) FROM stdin;
--SEQ_GEN_TABLE	0
--\.


--
-- Data for Name: u_sequences; Type: TABLE DATA; Schema: public; Owner: supplier_user
--

--COPY public.u_sequences (s_id, s_nextnum) FROM stdin;
--S_SEQ	3
--\.


--
-- Data for Name: s_component; Type: TABLE DATA; Schema: public; Owner: supplier_user
--

COPY public.s_component (comp_id, container_size, comp_cost, comp_desc, lead_time, comp_name, qty_demanded, qty_on_order, comp_site_id, comp_unit, comp_version) FROM stdin;
\.


--
-- Data for Name: s_purchase_order; Type: TABLE DATA; Schema: public; Owner: supplier_user
--

COPY public.s_purchase_order (po_number, po_sent_date, po_site_id, po_start_date, po_supp_id, po_version) FROM stdin;
\.


--
-- Data for Name: s_purchase_orderline; Type: TABLE DATA; Schema: public; Owner: supplier_user
--

COPY public.s_purchase_orderline (pol_number, pol_po_id, pol_location, pol_leadtime, pol_message, pol_qty, pol_balance, pol_p_id, pol_deldate, pol_version) FROM stdin;
\.


--
-- Data for Name: s_supp_component; Type: TABLE DATA; Schema: public; Owner: supplier_user
--

COPY public.s_supp_component (sc_p_id, sc_supp_id, sc_del_date, sc_discount, sc_price, sc_qty, sc_version) FROM stdin;
1	1	1	0	101.00	10	1
1	2	1	5	102.00	10	1
2	2	1	7	101.00	10	1
2	3	1	5	102.00	5	1
3	3	1	5	102.00	5	1
\.


--
-- Data for Name: s_supplier; Type: TABLE DATA; Schema: public; Owner: supplier_user
--

COPY public.s_supplier (supp_id, supp_contact, supp_name, supp_reply_url, supp_version, supp_ws_url, supp_city, supp_country, supp_phone, supp_state, supp_street1, supp_street2, supp_zip) FROM stdin;
1	contact	Supplier 1	http://localhost:9080/reply	1	http://localhost:9080/ws	city	country	0102020203	DE	street1	street2	zip
2	contact	Supplier 2	http://localhost:9080/reply	1	http://localhost:9080/ws	city	country	0102020203	DE	street1	street2	zip
3	contact	Supplier 3	http://localhost:9080/reply	1	http://localhost:9080/ws	city	country	0102020203	DE	street1	street2	zip
\.







--
-- PostgreSQL database dump complete
--

